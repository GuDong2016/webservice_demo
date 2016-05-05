package main.smart.llj.loginfo.service.impl;

import main.smart.llj.loginfo.dao.ILogInfoDao;
import main.smart.llj.loginfo.domain.LogInfo;
import main.smart.llj.loginfo.service.ILogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by dell on 2016/4/27.
 */
@Repository("logInfoService")
public class LogInfoServiceImpl implements ILogInfoService {
    @Autowired
    private ILogInfoDao logInfoDao;

    @Override
    public int saveLogInfo(LogInfo logInfo) {
        return this.logInfoDao.saveLogInfo(logInfo);
    }
}
