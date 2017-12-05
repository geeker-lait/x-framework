package com.tasfe.framework.encrypt.service.impls;

import com.tasfe.framework.encrypt.model.bo.KeyStoreInfos;
import com.tasfe.framework.encrypt.model.entity.KeyStore;
import com.tasfe.framework.encrypt.service.KeyStoreService;
import com.tasfe.framework.support.service.impls.CrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-12-05 11:29
 */
@Service
public class KeyStoreServiceImpl extends CrudServiceImpl<KeyStoreInfos,KeyStore,Long> implements KeyStoreService {
}