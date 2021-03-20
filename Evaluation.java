package HW_Answer;
//평가 클래스(평가 번호, 교사 번호, 학생 번호, 내용, 작성일)

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Evaluation {

    // 평가 번호
    private int id;
    // 해당 평가가 적힌 교사 번호
    private int teacherId;
    // 해당 평가를 적은 학생 번호
    private int studentId;
    // 내용
    private String content;
    // 작성일
    private Calendar writtenDate;
    
    // SimpleDateFormat에서 사용할 형식 스트링
    private final String DATE_FORMAT = new String("yy년 MM월 dd일");
    
    

    // getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(Calendar writtenDate) {
        this.writtenDate = writtenDate;
    }

    // equals()
    public boolean equals(Object o) {
        if (o instanceof Evaluation) {
            Evaluation e = (Evaluation) o;
            if (id == e.id) {
                return true;
            }
        }

        return false;
    }

    // 평가 정보를 출력하는 print()
    public void print(Teacher t, Student s) {
        // 교사이름: 평가내용 - 학생이름(날짜)
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT); 
        System.out.printf("%s: %s - %s (%s)\n", t.getName(), content, s.getName(), sdf.format(writtenDate.getTime()));
        
        
        
        
        
        
        
        
        
        
        
        
    }

}
