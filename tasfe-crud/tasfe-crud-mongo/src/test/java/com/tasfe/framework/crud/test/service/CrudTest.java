package com.tasfe.framework.crud.test.service;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.enums.Operator;
import com.tasfe.framework.crud.api.params.Page;
import com.tasfe.framework.crud.core.CrudTemplate;
import com.tasfe.framework.crud.test.model.entity.Member;
import com.tasfe.framework.crud.test.model.entity.Resources;
import com.tasfe.framework.crud.test.model.entity.User;
import com.tasfe.framework.crud.test.model.entity.UserSession;
//import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Lait on 2017/4/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springs/spring-mybatis.xml"})
//@Transactional
public class CrudTest {

    @Resource(name="mysql")
    CrudTemplate crudTemplate;

    private User getUser(){
        User u = new User();
        AtomicLong al = new AtomicLong();
        Random random = new Random();
        u.setUserId(Long.valueOf(random.nextInt()));
        u.setDeptId(1L);
        u.setOrderId(11L);
        u.setEmail("lait.zhang@gmail.com");
        u.setMobilePhone("15801818092");
        u.setPassword("123");
        u.setBirth(new Date());
        return u;
    }

    private List<User> getUsers(int count){
        List<User> userList = new ArrayList<User>();
        for(int i=0;i<count;i++){
            userList.add(getUser());
        }
        return userList;
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testInsert() throws Exception {

        crudTemplate.save(getUser());


        //crudTemplate.save(getUsers(10));



       /* Member member = new Member();
        member.setEmail("lait");
        member.setUserId(11L);
        member.setDeptId(1);
        member.setOrderId(111);
        crudTemplate.save(member);*/

    }




    @Test
    public void testSelect() throws Exception {
        User user;

        user = crudTemplate.get(User.class,96L);
        System.out.println(user + "=====" + user);

        List<User> users = crudTemplate.list(User.class,96L,98L);
        System.out.println("users.list====="+ users);


        Criteria criteria = Criteria.from(User.class);
        users = crudTemplate.find(user,criteria);
        System.out.println("users====="+ users);


        criteria = Criteria.from(User.class).limit(0,10);
        users = crudTemplate.find(user,criteria);
        System.out.println("users.limit ====="+ users);



        user.setUserId(-996253669L);
        criteria = Criteria.from(User.class).where().camelCase(true).and("userId",Operator.EQ).endWhere();
        users = crudTemplate.find(user,criteria);
        System.out.println(users +"====="+ users);



        user.setEmail("lait.zhan");
        criteria = Criteria.from(User.class).where().camelCase(false).and("email",Operator.LLIKE).endWhere();
        users = crudTemplate.find(user,criteria);
        System.out.println("users.like ==============================="+ users);




        Member member = new Member();
        member.setEmail("lait.zhang@gmail.com");
        member.setId(1L);
        member.setUserId(11L);
        criteria = Criteria.from(Member.class).where().and("userId",Operator.EQ).endWhere();
        List<Member> userass = crudTemplate.find(member,criteria);
        System.out.println(userass);

    }


    @Test
    public void testFind() throws Exception {
        User user = new User();
        //user.setEmail("lait.zhan");
        user.setMobilePhone("15801818092");
        Criteria criteria = Criteria.from(User.class);
        List<User> users = crudTemplate.find(user,criteria);
        System.out.println("users.like ==============================="+ users);
    }


    @Test
    public void testQuery() throws Exception {
        UserSession root = new UserSession();

        List<UserSession> temptList = crudTemplate.find(root, Criteria.from(UserSession.class).where().and("status", Operator.EQ, 1).and("user_id", Operator.EQ, 1L).endWhere());

    }


    @Test
    public void testSum() throws Exception {


        BigDecimal sum = crudTemplate.sum(new User(), Criteria.from(User.class).sum("job").where().endWhere());

        sum = crudTemplate.sum(new User(), Criteria.from(User.class).sum("job").where().endWhere());

        System.out.println(sum);

    }



    public List<ResourcesTree> treeMenuList(List<Resources> menuList, long parentId) {
        List<ResourcesTree> childMenu = new ArrayList<>();
        for (Resources object : menuList) {
            ResourcesTree jsonMenu = new ResourcesTree();
            BeanUtils.copyProperties(object,jsonMenu);

            long menuId = jsonMenu.getId();
            long pid = jsonMenu.getPid();
            if (parentId == pid) {
                List<ResourcesTree> c_node = treeMenuList(menuList, menuId);
                jsonMenu.setChilds(c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }


    @Test
    public void testTree() throws Exception {
        //13816186542 冯 科技创新补贴
        Resources resources = new Resources();
        List<Resources> res = crudTemplate.tree(resources,Criteria.from(Resources.class));
        List<ResourcesTree> rr = treeMenuList(res,1L);

        //ResourcesTree resourcesTree = new ResourcesTree();
        /*List<ResourcesTree> resourcesTrees = new ArrayList<>();
        for(Resources r :res){

            if(r.getPid()!= null){

            }

        }*/
        System.out.println(res.size());
    }



    @Test
    public void testUpd() throws Exception {

//        User user = new User();
//        user.setId(95L);
//        user.setEmail("zhang_yingliang@163.com");
//        user.setOfficePhone("1234567");
//        crudTemplate.update(user,Criteria.from(User.class));




        List<User> users  = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User();
            user.setId(98L+i);
            user.setMobilePhone("1111");
            user.setEmail("lief@163.com---"+ i);
            users.add(user);
        }

        // user 实体填充具体值，根据id主键全量更新
        crudTemplate.update(users,Criteria.from(User.class));

        // user 实体填充具体值，默认根据id主键更新(更具指定的字段值)/如果user没有主键报错
        crudTemplate.update(users,Criteria.from(User.class));

        // user 实体填充具体值，不根据id主键更新(更具指定的字段值)
         crudTemplate.update(users,Criteria.from(User.class).whioutId().where().and("userId", Operator.EQ).endWhere());

        // user 实体填充具体值，更新指定的字段（谨慎使用！！！！！！！！！！！！！！，不建议不根据id更新）
        crudTemplate.update(users,Criteria.from(User.class).fields("mobilePhone").whioutId().where().and("userId", Operator.EQ).endWhere());

        // user 实体填充具体值，更新指定的字段,并设置更新的默认值
        crudTemplate.update(users,Criteria.from(User.class).fields("mobilePhone").whioutId().where().and("userId", Operator.EQ,123).endWhere());

    }


    @Test
    public void testDel() throws Exception {

        Long[] ids = new Long[]{95L,96L,97L};
        crudTemplate.delete(User.class,ids);


        User user = new User();
        user.setEmail("lait.zhang@gmail.com");
        crudTemplate.delete(user,Criteria.from(User.class).where().and("email",Operator.EQ).endWhere());

    }


    @Test
    public void countTest() throws Exception {

        User user = new User();
        user.setEmail("zhang_yingliang");
        Long count = crudTemplate.count(user,Criteria.from(User.class).where().and("email",Operator.RLIKE).endWhere());
        System.out.println("========================" + count);
    }


    @Test
    public void pagingTest() throws Exception {

        User user = new User();
        user.setEmail("zhang_yingliang");
        //user.setUserId(1098831561L);
        Page<User> page = crudTemplate.paging(user,Criteria.from(User.class));
        System.out.println("========================" + page);


        user = new User();
        user.setEmail("zhang_yingliang");
        //user.setUserId(1098831561L);
        page = crudTemplate.paging(user,Criteria.from(User.class).limit(0,10));
        System.out.println("========================" + page);



        user = new User();
        user.setEmail("zhang_yingliang");
        user.setUserId(1098831561L);
        page = crudTemplate.paging(user,Criteria.from(User.class).where().and("email",Operator.RLIKE).and("userId",Operator.EQ).endWhere().limit(0,10));
        System.out.println("========================" + page);

    }




    /*@Test
    public void testBusinessInsert() throws Exception {
        User u = getUser();
        crudBusiness.insert(u);


    }

    @Test
    public void testUBusinessInsert() throws Exception {
        User u = getUser();
        userBusiness.findUsers();
    }*/



    @Test
    public void testTemplateInsert() throws Exception {
        User u = getUser();
        crudTemplate.save(u);
    }

    @Test
    public void testOperationInsert() throws Exception {
        User u = getUser();
        crudTemplate.save(u);
    }


    @Test
    public void testOperationInsertBatch() throws Exception {
        List<User> userList = getUsers(10);
        //mysqlTemplate.batchInsert(userList);
        System.out.println("====" + userList.size());
        for(User user:userList){
            System.out.println(user.getUserId() + "::::" + user.getDeptId());
        }
    }



    
    
    @Test
    public void testOperationFind() throws Exception {
        List<User> userList = getUsers(10);
        //mysqlTemplate.batchInsert(userList);
        System.out.println("====" + userList.size());
        for(User user:userList){
            System.out.println(user.getUserId() + "::::" + user.getDeptId());
        }
    }



    @Test
    public void  testhefs() throws Exception {
//        User record = new User();
//        record.setJob("job");
//        record.setEmail("111");
//        record.setOfficePhone("11");
//        List<User> list = crudTemplate.find(record, Criteria.from(User.class).where().and("office_phone",Operator.EQ,"12").endWhere());
//        System.out.println("4");

        User record = new User();
        crudTemplate.sum(record,Criteria.from(User.class));


        System.out.println();



    }

}
