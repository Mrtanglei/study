package com.shiro.session;

import com.shiro.util.RedisDUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SessionDao extends AbstractSessionDAO {

    private static final String SESSION_KEY = "SHIRO_SESSION_";
    private final Logger logger = LoggerFactory.getLogger(SessionDao.class);
    @Autowired
    private RedisDUtils redisDUtils;

    protected Serializable doCreate(Session session) {
        Serializable serializable = generateSessionId(session);
        saveSession(session);
        //将session和sessionId进行捆绑
        assignSessionId(session, serializable);
        return serializable;
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = redisDUtils.getKey(session.getId().toString(), SESSION_KEY);
            byte[] value = SerializationUtils.serialize(session);
            redisDUtils.setValue(key, value);
            redisDUtils.expire(key, 600);
        }
    }

    protected Session doReadSession(Serializable sessionId) {
        logger.info("====in doReadSession==={}===", sessionId);
        byte[] value = redisDUtils.getValue(redisDUtils.getKey(sessionId.toString(), SESSION_KEY));
        if (value != null) {
            return (Session) SerializationUtils.deserialize(value);
        }
        return null;
    }

    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    public void delete(Session session) {
        redisDUtils.deleteValue(redisDUtils.getKey(session.getId().toString(), SESSION_KEY));
    }

    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = redisDUtils.keys(SESSION_KEY);
        if (keys != null) {
            Set<Session> result = new HashSet<>();
            keys.forEach(key -> {
                result.add((Session) SerializationUtils.deserialize(redisDUtils.getValue(key)));
            });
        }
        return null;
    }
}
