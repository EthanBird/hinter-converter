package vip.esyc.search.hint.handler.etl;

import java.util.Objects;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.handler.etl.handler
 * @ClassName: ETLHandleable
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 16:16
 * @Version: 1.0
 */
public interface HintETLHandler {
    String doEtl(String sourceUrl);

}
