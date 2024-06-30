package cn.zzuer.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

//数据库表单
@Data
@TableName("law")
public class Law {
    //案件id，即案件号
    @TableId(type = IdType.INPUT)
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
    //标签——————————目前打算废弃
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
}
