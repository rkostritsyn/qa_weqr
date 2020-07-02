package com.griddynamics.meetapp.configuration;

import com.griddynamics.meetapp.util.Identifiable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class IdentifiableUUIDGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if(object instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) object;
            Serializable id = identifiable.getId();
            if(id != null) {
                return id;
            }
        }
        return super.generate(session, object);
    }
}
