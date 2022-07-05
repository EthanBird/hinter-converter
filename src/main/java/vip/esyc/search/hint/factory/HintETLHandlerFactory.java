package vip.esyc.search.hint.factory;

import vip.esyc.search.hint.handler.etl.*;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.factory.base
 * @ClassName: ETLHandlerFactory
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 16:11
 * @Version: 1.0
 */
public class HintETLHandlerFactory {

    public static HintETLHandlerFactory getFactory() {
        return InnerClass.INSTANCE;
    }
    private static class InnerClass{
        private static HintETLHandlerFactory INSTANCE = new HintETLHandlerFactory();
    }
    public HintETLHandler getHintETLHandler(String site) {
        HintETLHandler hintETLHandler = AbstractHintETLHandler.getHandler();
        if("bilibili".equals(site)){
            hintETLHandler = BilibiliHintETLHandler.getHandler();
        }
        if("weibo".equals(site)){
            hintETLHandler = WeiboHintETLHandler.getHandler();
        }
        if("maven".equals(site)){
            hintETLHandler = MavenHintETLHandler.getHandler();
        }
        return hintETLHandler;
    }
}
