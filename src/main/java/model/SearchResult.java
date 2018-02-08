package model;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public class SearchResult {
    
    private Long hitNum = -1L;
    
    private String content = "";

    public Long getHitNum() {
        return hitNum;
    }

    public void setHitNum(Long hitNum) {
        this.hitNum = hitNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HitNum=" + hitNum + "\r\n");
        stringBuilder.append(content);
        return stringBuilder.toString();
    }
}
