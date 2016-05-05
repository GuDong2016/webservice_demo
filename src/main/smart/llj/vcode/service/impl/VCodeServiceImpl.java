package main.smart.llj.vcode.service.impl;

import main.smart.llj.vcode.dao.IVCodeDao;
import main.smart.llj.vcode.domain.VCode;
import main.smart.llj.vcode.service.IVCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2016/5/3.
 */
@Service("vCodeService")
public class VCodeServiceImpl implements IVCodeService {
    @Autowired
    IVCodeDao vCodeDao;
    @Override
    public int saveVCode(VCode vCode) {
        return this.vCodeDao.saveVCode(vCode);
    }
}
