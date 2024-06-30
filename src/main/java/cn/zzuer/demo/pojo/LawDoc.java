package cn.zzuer.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LawDoc {
    //测试用，目前页面较为简单

    //案件id，即案件号
    private String caseId;
    //原告
    private String plaintiff;
    //被告
    private String defendant;
    //原告律师
    private String plaintiffLawyer;
    //被告律师
    private String defendantLawyer;
    //案件名称
    private String caseName;
    //法院
    private String court;
    //法官
    private String judge;
    //标签
    //private List<String> label;
    //案由
    private String causeOfAction;
    //下面觉得枚举会更好
    //判决结果
    private String judgmentResult;
    //法院层级
    private String hierarchy;
    //地区
    private String location;
    //内容
    private String text;

    //与数据库表单对应的构造方法

    public LawDoc(Law law){
        this.caseId = law.getCaseId();
        this.caseName = law.getCaseName();
        this.location = law.getLocation();
        this.plaintiff = law.getPlaintiff();
        this.plaintiffLawyer = law.getPlaintiffLawyer();
        this.defendant = law.getDefendant();
        this.defendantLawyer = law.getDefendantLawyer();
        this.court = law.getCourt();
        this.judge = law.getJudge();
        //this.label = law.getLabel();
        this.causeOfAction = law.getCauseOfAction();
        this.judgmentResult = law.getJudgmentResult();
        this.hierarchy = law.getHierarchy();
        this.text = law.getText();
    }
}
