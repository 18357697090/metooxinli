package com.metoo.web.config.tools;

import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.pojo.ps.model.PsScaleProblemModel;
import com.metoo.pojo.ps.model.PsScaleResultModel;

import java.util.List;
import java.util.Map;

public class CalculateTheScore {

    // TODO 保存做题记录

    public static String calculate(Result result){
        int scaleId=result.getScaleId();
        Repository repository=new Repository();
        List<PsScaleResultModel> psScaleResultModels = repository.getScaleResult(scaleId);
        List<OptionsResult> results=result.getResults();
        PsScaleOptionsModel options = repository.findOptions(scaleId);
        List<PsScaleProblemModel> problem = repository.findProblem(scaleId);
        String stroptions= options.getOptions();
        String[] strArr= stroptions.split("，");
        int optionsLength=strArr.length+1;
        int length = problem.size()+1;
        int [][] x=new int[length][optionsLength];
        for (int i=1;i<length;i++){
            for (int j=1;j<optionsLength;j++){
                if(problem.get(i-1).getType()==1){
                    x[i][j]=j;
                }else {
                    x[i][j]=optionsLength-j;
                }
            }
        }
        int score=0;
        for(int i=0 ; i<length-1;i++){
            OptionsResult option = results.get(i);
            score += x[i+1][option.getAnswerIndex()+1];
        }
        int a=0;
        int b=0;
        int c=0;
        for (int i=1;i<length;i++){
            OptionsResult option = results.get(i-1);
            int factor = problem.get(i-1).getFactorType();
            if(factor==1){
                a += x[i][option.getAnswerIndex()];
            }else if (factor==2){
                b += x[i][option.getAnswerIndex()];
            }else if(factor==3){
                c += x[i][option.getAnswerIndex()];
            }
        }
        int d=(a+b);
        int qin=0;
        int jiao=0;
        if (d>36){qin=1;}
        if(c>18){jiao=1;}
        if(qin==1&&jiao==0){
            return "您的成人依恋量表测量结果为"+score+ "，显示您是安全型依恋。\n" +
                    "安全型依恋的形成，离不开儿童期时父母等养育者对儿童的良好照料，在儿童刚接触这个世界时处于一个安全的环境中，进而使得儿童在生活中拥有更高得安全感，更低的恐惧和焦虑感。\n" +
                    "Hazan和Shaver的研究显示，安全依恋类型的人有浪漫热情的爱，较少出现极端的抛弃自我的、完全奉献式的爱。在人际关系中，安全型依恋的人常常有积极的关系，对与其有亲密关系的人相当信任并有充分的自信。\n";
        }else if(qin==1&&jiao==1){
            return "您的成人依恋量表测量结果为"+score+"   ，显示您是痴迷型依恋。\n" +
                    "这种依恋类型的人的典型童年经历是由于其母亲等养育者有抑郁等心理疾病，或者有残疾等现象使得母亲没有很好地发挥母亲对子女的爱和照料。儿童甚至会在家庭中扮演母亲的角色去照顾其他的家庭成员，这种经历会产生一种信念，让个体认为自己只有不断地给予别人帮助才能产生情感联结。\n" +
                    "C·Baetholomew, Horowitz（1991）发现这种依恋类型的个体往往把别人的感受和价值看得比自己更重要，人际关系中经常出现焦虑和情绪化的特征。\n";
        }else if(qin==0&&jiao==0){
            return "您的成人依恋量表测量结果为  "+score+" ，显示您是矛盾型依恋。\n" +
                    "具有这种依恋类型的个体可能在童年时代长期处于一种害怕丧失照料者或者害怕被抛弃的状态，例如父母对孩子的呼求和需要没有回应，或者孩子的需要被拒绝；父母由于监禁等各种原因不在家，儿童可能被养育机构或亲戚抚养，住所可能变化不定；也有可能是父母经常以自己将要离开孩子威胁孩子听话，或者伤害孩子、自己或配偶时，孩子会产生一种心理遗弃感。这些不稳定、不安全的行为都会使得孩子产生焦虑反应。\n" +
                    "研究显示，具有这种依恋类型的个体成年之在人际关系中会极其渴望爱与支持，由于强烈地害怕被依恋对象抛弃，他们可能会表现出过度地依赖和信任；但是当真正面临被拒绝或抛弃时，又会显得极度绝情，不相信对方，总是将对方推开，并宣称自己不需要对方，以此保护自己免受被遗弃的伤痛。\n";
        }else {
            return "您的成人依恋量表测量结果为 "+score+"  ，显示您是恐惧型依恋。\n" +
                    "Bowlby（1977）认为这种依赖类型的个体可能拥有和矛盾型依恋个体一样的童年经历。这些个体可能在童年时代长期处于一种害怕丧失照料者或者害怕被抛弃的状态，例如父母对孩子的呼求和需要没有回应，或者孩子的需要被拒绝；父母由于监禁等各种原因不在家，儿童可能被养育机构或亲戚抚养，住所可能变化不定；也有可能是父母经常以自己将要离开孩子威胁孩子听话，或者伤害孩子、自己或配偶时，孩子会产生一种心理遗弃感。这些不稳定、不安全的行为都会使得孩子产生焦虑反应。\n" +
                    "研究显示这种人在成年之后的人际关系中因为小时候常年处于恐惧被抛弃的状态，对自己的评价也会随之降低，认为自己是不值得被爱的，变得自卑，长期处于焦虑状态；不愿意相信他人，因为害怕被他人拒绝。\n";
        }

    }

}
