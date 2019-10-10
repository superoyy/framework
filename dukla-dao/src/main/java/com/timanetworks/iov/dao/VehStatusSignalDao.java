package com.timanetworks.iov.dao;

import com.timanetworks.iov.core.jpa.dao.HibernateBaseDao;
import com.timanetworks.iov.domain.VehStatusSignal;
import org.springframework.stereotype.Repository;

/**
 * 车系的原始车况信号和标准车况信号对照表（广丰）
 *
 * @author taoningbo
 * @since 1.0.0
 */
@Repository
public class VehStatusSignalDao extends HibernateBaseDao<VehStatusSignal> {
}
