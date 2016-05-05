package main.smart.llj.vcode.dao;

import main.smart.llj.base.dao.IDao;
import main.smart.llj.vcode.domain.VCode;
import org.springframework.stereotype.Repository;

/**
 * Created by dell on 2016/5/3.
 */
@Repository("vCodeDao")
public interface IVCodeDao extends IDao {
    public int saveVCode(VCode vCode);
}
