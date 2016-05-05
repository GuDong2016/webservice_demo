package main.smart.llj.loginfo.dao;

import main.smart.llj.base.dao.IDao;
import main.smart.llj.loginfo.domain.LogInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by dell on 2016/4/27.
 */
@Repository("logInfoDao")
public interface ILogInfoDao extends IDao{
    public int saveLogInfo(LogInfo logInfo);
}
