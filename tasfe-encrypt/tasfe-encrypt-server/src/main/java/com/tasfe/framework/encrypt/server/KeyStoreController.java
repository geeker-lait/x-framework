package com.tasfe.framework.encrypt.server;

import com.tasfe.framework.encrypt.model.bo.KeyStoreInfos;
import com.tasfe.framework.encrypt.model.entity.KeyStore;
import com.tasfe.framework.encrypt.service.KeyStoreService;
import com.tasfe.framework.support.model.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-12-05 11:30
 */
@RestController
public class KeyStoreController {

    @Autowired
    private KeyStoreService keyStoreService;

    public ResponseData addKey(@RequestBody KeyStoreInfos keyStoreInfos){
        KeyStore keyStore = new KeyStore();
        BeanUtils.copyProperties(keyStoreInfos,keyStore);
        keyStoreService.save(keyStore);
        return ResponseData.success();
    }
}