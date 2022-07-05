package vip.esyc.search.hint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.esyc.search.hint.factory.HintETLHandlerFactory;

/**
 * @ProjectName: hinter-converter
 * @Package: vip.esyc.search.hint.controller
 * @ClassName: HintController
 * @Author: Syicong
 * @Description:
 * @Date: 2022/7/5 15:39
 * @Version: 1.0
 */
@RestController("ApiContentArchiveController")
@RequestMapping("/hint/api")
public class HintController {

    @GetMapping
    public String hallo() {
        return "Hello API";
    }

    @GetMapping("/hintSearch/{site}/{query}")
    public String hintQuery(@PathVariable String site, @PathVariable String query){
        return HintETLHandlerFactory.getFactory().getHintETLHandler(site).doEtl(query);
    }

}
