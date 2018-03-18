package com.gonggongjohn.realtimenote.ParseData;

public class DPR {
    public static String resolvePostag(String postag){
        switch (postag){
            case "n": return "普通名词";
            case "nr": return "人名";
            case "nz": return "其他名词";
            case "a": return "形容词";
            case "m": return "数量词";
            case "c": return "连词";
            case "f": return "方位名词";
            case "ns": return "地名";
            case "v": return "普通动词";
            case "ad": return "副形词";
            case "q": return "量词";
            case "u": return "助词";
            case "s": return "处所名词";
            case "nt": return "机构团体名";
            case "vd": return "副动词";
            case "an": return "名形词";
            case "r": return "代词";
            case "xc": return "其他虚词";
            case "t": return "时间名词";
            case "nw": return "作品词";
            case "vn": return "动名词";
            case "d": return "副词";
            case "p": return "介词";
            case "w": return "标点符号";
            case "Ag": return "形语素";
            case "b": return "区别词";
            case "dg": return "副语素";
            case "e": return "叹词";
            case "g": return "语素";
            case "h": return "前接成分";
            case "i": return "成语";
            case "j": return "简称略语";
            case "k": return "后接成份";
            case "l": return "习用语";
            case "Ng": return "名语素";
            case "o": return "拟声词";
            case "tg": return "时语素";
            case "vg": return "动语素";
            case "x": return "非语素词";
            case "z": return "状态词";
            case "un": return "未知词";
            default: return postag;
        }
    }
}
