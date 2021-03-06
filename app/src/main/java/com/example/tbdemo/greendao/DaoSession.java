package com.example.tbdemo.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.tbdemo.bean.CartDaoBean;
import com.example.tbdemo.bean.UserBean;

import com.example.tbdemo.greendao.CartDaoBeanDao;
import com.example.tbdemo.greendao.UserBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cartDaoBeanDaoConfig;
    private final DaoConfig userBeanDaoConfig;

    private final CartDaoBeanDao cartDaoBeanDao;
    private final UserBeanDao userBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cartDaoBeanDaoConfig = daoConfigMap.get(CartDaoBeanDao.class).clone();
        cartDaoBeanDaoConfig.initIdentityScope(type);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        cartDaoBeanDao = new CartDaoBeanDao(cartDaoBeanDaoConfig, this);
        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);

        registerDao(CartDaoBean.class, cartDaoBeanDao);
        registerDao(UserBean.class, userBeanDao);
    }
    
    public void clear() {
        cartDaoBeanDaoConfig.clearIdentityScope();
        userBeanDaoConfig.clearIdentityScope();
    }

    public CartDaoBeanDao getCartDaoBeanDao() {
        return cartDaoBeanDao;
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

}
