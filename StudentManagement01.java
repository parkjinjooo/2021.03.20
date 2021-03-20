package HW_Answer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import util.ScannerUtil;

public class StudentManagement01 {
    // 학생 목록을 저장할 ArrayList<> 객체
    private static final ArrayList<Student> LIST_STUDENT = new ArrayList<>();
    // 선생 목록을 저장할 ArrayList<> 객체
    private static final ArrayList<Teacher> LIST_TEACHER = new ArrayList<>();
    // 평가 목록을 저장할 ArrayList<> 객체
    private static final ArrayList<Evaluation> LIST_EVALUATION = new ArrayList<>();
    // 회원 가입시 학생의 id 값을 자동으로 처리해줄 int 변수
    private static int curStudentId = 1;
    // 회원 가입시 선생의 id 값을 자동으로 처리해줄 int 변수
    private static int curTeacherId = 1;
    // 평가 등록시 평가의 id 값을 자동으로 처리해줄 int 변수
    private static int curEvaluationId = 1;
    // 학생 로그인 시 로그인한 학생의 정보를 저장할 Student 객체
    private static Student logInStudent = null;
    // 교사 로그인 시 로그인한 교사의 정보를 저장할 Teacher 객체
    private static Teacher logInTeacher = null;
    // 입력을 처리할 Scanner 객체
    private static Scanner scanner = new Scanner(System.in);
    // 로그인한 사람의 소속 반 학생들을 따로 저장하는 ArrayList<> 객체
    private static ArrayList<Student> groupStudents = new ArrayList<>();

    public static void main(String[] args) {
        init();
        showIndex();
    }

    // 1. 초기화 작업을 위한 메소드
    private static void init() {
        // Student 객체, Teacher 객체, Evaluation 객체를 여러개 만들어서 각각 리스트에 추가한다.
        // 또한, 데이터를 다양하게 해서 여러가지 결과를 볼 수 있도록 한다.

        // 1. Student 객체 추가
        Student s1 = new Student();
        s1.setId(curStudentId++);
        s1.setUsername("s1");
        s1.setPassword("1");
        s1.setGroup(1);
        s1.setName("학생1");
        s1.setScoreInputSwitch(false);

        Student s2 = new Student();
        s2.setId(curStudentId++);
        s2.setUsername("s2");
        s2.setPassword("2");
        s2.setGroup(1);
        s2.setName("학생2");
        s2.setScoreInputSwitch(true);
        s2.setKorean(80);
        s2.setEnglish(80);
        s2.setMath(81);

        Student s3 = new Student();
        s3.setId(curStudentId++);
        s3.setUsername("s3");
        s3.setPassword("3");
        s3.setGroup(1);
        s3.setName("학생3");
        s3.setScoreInputSwitch(true);
        s3.setKorean(83);
        s3.setEnglish(83);
        s3.setMath(81);

        Student s4 = new Student();
        s4.setId(curStudentId++);
        s4.setUsername("s4");
        s4.setPassword("4");
        s4.setGroup(2);
        s4.setName("학생4");
        s4.setScoreInputSwitch(false);

        Student s5 = new Student();
        s5.setId(curStudentId++);
        s5.setUsername("s5");
        s5.setPassword("5");
        s5.setGroup(2);
        s5.setName("학생5");
        s5.setScoreInputSwitch(true);
        s5.setKorean(81);
        s5.setEnglish(82);
        s5.setMath(83);

        // s1~s5를 LIST_STUDENT에 추가해준다.
        LIST_STUDENT.add(s1);
        LIST_STUDENT.add(s2);
        LIST_STUDENT.add(s3);
        LIST_STUDENT.add(s4);
        LIST_STUDENT.add(s5);

        // 2. 교사 객채
        Teacher t1 = new Teacher();
        t1.setId(curTeacherId++);
        t1.setUsername("t1");
        t1.setPassword("1");
        t1.setName("교사1");
        t1.setGroup(1);

        Teacher t2 = new Teacher();
        t2.setId(curTeacherId++);
        t2.setUsername("t2");
        t2.setPassword("2");
        t2.setName("교사2");
        t2.setGroup(2);

        // t1, t2를 LIST_TEACHER에 추가해준다.
        LIST_TEACHER.add(t1);
        LIST_TEACHER.add(t2);

        // 3.Evaluation 객체
        Evaluation e1 = new Evaluation();
        e1.setId(curEvaluationId++);
        e1.setStudentId(1);
        e1.setTeacherId(2);
        e1.setContent("좋아요");
        e1.setWrittenDate(Calendar.getInstance());

        Evaluation e2 = new Evaluation();
        e2.setId(curEvaluationId++);
        e2.setStudentId(2);
        e2.setTeacherId(2);
        e2.setContent("매우 좋아요");
        e2.setWrittenDate(Calendar.getInstance());

        Evaluation e3 = new Evaluation();
        e3.setId(curEvaluationId++);
        e3.setStudentId(4);
        e3.setTeacherId(1);
        e3.setContent("싫어요");
        e3.setWrittenDate(Calendar.getInstance());

        // e1~e3을 LIST_EVALUATION에 추가해준다.
        LIST_EVALUATION.add(e1);
        LIST_EVALUATION.add(e2);
        LIST_EVALUATION.add(e3);

    }

    // 2. 인덱스를 보여주는 메소드
    private static void showIndex() {

        while (true) {
            String message = new String("1. 로그인 2. 회원 가입 3. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

            if (userChoice == 1) {
                // 로그인 메소드를 실행 후
                // logIn 관련 객체를 검사하여 둘 중 하나가 null 이 아닐 때에만
                // 메뉴를 보여주는 메소드 실행
                logIn();

                if (logInStudent != null || logInTeacher != null) {
                    // 메뉴 메소드를 실행
                    showMenu();
                }
            } else if (userChoice == 2) {
                // 회원 가입 메소드 실행
                register();

            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }
    }

    // 2-1. 로그인 메소드
    private static void logIn() {

        while (true) {
            // 1. 로그인할 유형을 입력 받는다.
            String message;
            message = new String("로그인 하실 유형을 선택하세요. (1: 교사 2: 학생) 0을 입력하시면 인덱스 화면으로 돌아갑니다.");
            int type = ScannerUtil.nextInt(scanner, message, 0, 2);

            if (type == 0) {
                break;
            }
            message = new String("아이디: ");
            String username = ScannerUtil.nextLine(scanner, message);

            message = new String("비밀번호: ");
            String password = ScannerUtil.nextLine(scanner, message);

            // 2. type에 값에 따라서
            // 메소드를 이용하여 logInStudent 혹은 logInTeacher의 값을 할당해주거나
            // 이 logIn() 메소드를 종료 시킨다.
            setLogIn(type, username, password);

            // 3. 만약 logInStudent나 logInTeacher 중 하나라도 null이 아닐 경우
            // break 한다.
            if (logInStudent != null || logInTeacher != null) {
                break;
            }

            // 4. 만약 위에서 break가 되지 않았을 경우
            // "잘못 입력하셨습니다. 아이디와 비밀번호를 확인해주세요" 라는 메시지를 출력해주고
            // 다시 위로 올라간다.
            System.out.println("잘못 입력하셨습니다. 아이디와 비밀번호를 확인해주세요");

        }
    }

    // 2-1-1. 아이디와 비밀번호를 입력받아서 로그인 객체를 할당하는 메소드
    private static void setLogIn(int type, String username, String password) {
        if (type == 1) {
            // 아이디와 비밀번호를 담을 Teacher 임시 객체
            Teacher temp = new Teacher();
            // 임시 객체에 username과 password를 넣어준다.
            temp.setUsername(username);
            temp.setPassword(password);

            for (Teacher t : LIST_TEACHER) {
                // temp의 auth 기능을 실행을 해서
                // true가 나오는 t 객체를
                // logInTeacher에 넣어준다.

                if (temp.auth(t)) {
                    logInTeacher = t;
                }

            }
        } else if (type == 2) {
            // username과 password를 담을 Student 임시 객체
            Student temp = new Student();

            // 임시 객체에 username과 password를 넣어준다.
            temp.setUsername(username);
            temp.setPassword(password);

            for (Student s : LIST_STUDENT) {
                // temp의 auth 기능을 실행을 해서
                // true가 나오는 객체를
                // logInStudent에 넣어준다.
                if (temp.auth(s)) {
                    logInStudent = s;
                }

            }

        }

    }

    // 2-2. 회원가입 메소드
    private static void register() {
        // 입력에서 사용할 String message
        String message;

        // 1. 교사인지 학생인지 확인
        message = new String("가입할 유형을 입력하세요. (1: 교사 2: 학생)");
        int type = ScannerUtil.nextInt(scanner, message, 1, 2);

        // 2. 로그인 시 사용할 아이디, 로그인 시 사용할 비밀번호, 이름, 소속 반을 받는다.
        // 단, 로그인 시 사용할 아이디는 각각의 목록애서 중복이 없어야 한다.

        // 2-1. 아이디 입력 받기
        message = new String("사용하실 아이디를 입력해주세요.");
        String username = ScannerUtil.nextLine(scanner, message);
        // 2-1-1. 입력 받은 username이 해당 리스트에 존재하는지 체크해서 존재하면
        // 존재하지 않는 username 값이 들어올때까지 다시 입력을 받는다.

        // 중복 여부를 저장할 boolean 변수 validateUsername

        while (validateUsername(type, username)) {
            System.out.println("이미 존재하는 아이디입니다.");
            username = ScannerUtil.nextLine(scanner, message);
        }

        // 2-2. 비밀번호 입력 받기
        message = new String("사용하실 비밀번호를 입력해주세요.");
        String password = ScannerUtil.nextLine(scanner, message);

        // 2-3. 이름 입력 받기
        message = new String("이름을 입력해주세요.");
        String name = ScannerUtil.nextLine(scanner, message);

        // 2-4. 소속반 입력 받기
        message = new String("반을 입력해주세요.");
        int group = ScannerUtil.nextInt(scanner, message);

        // 3. 각각에 맞춘 객체를 생성하여 해당 리스트에 추가한다.
        if (type == 1) {
            // 교사 객체를 만들어서
            // 정보를 넣고
            // 교사 리스트에 추가한다.

            Teacher t = new Teacher();
            // Teacher 객체 t에 교사 번호를 넣는다.
            t.setId(curTeacherId++);

            // Teacher 객체 t에 교사 아이디를 넣는다.
            t.setUsername(username);

            // Teacher 객체 t에 교사 이름을 넣는다.
            t.setName(name);

            // Teacher 객체 t에 교사 비밀번호를 넣는다.
            t.setPassword(password);

            // Teacher 객체 t에 교사 소속 반을 넣는다.
            t.setGroup(group);

            // LIST_TEACHER에 추가한다.
            LIST_TEACHER.add(t);

        } else if (type == 2) {
            // 학생 객체를 만들어서
            // 정보를 넣고
            // 학생 리스트에 추가한다.

            Student s = new Student();

            // Student 객체 s에 학생 번호를 넣는다.
            s.setId(curStudentId++);

            // Student 객체 s에 학생 아이디를 넣는다.
            s.setUsername(username);

            // Student 객체 s에 학생 비밀번호를 넣는다.
            s.setPassword(password);

            // Student 객체 s에 학생 이름을 넣는다.
            s.setName(name);

            // Student 객체 s에 힉생 소속 반을 넣는다.
            s.setGroup(group);

            // Student 객체 s에 입력 여부 스위치를 false로 한다.
            s.setScoreInputSwitch(false);

            // LIST_STUDENT에 추가한다.
            LIST_STUDENT.add(s);

        }
    }

    // 2-3. 해당 종류의 리스트에 똑같은 username이 있는지 확인하는 메소드
    private static boolean validateUsername(int type, String username) {
        if (type == 1) {
            // 콜렉션의 경우, 단순히 출력이나 비교를 할 때에는
            // for문을 좀 간략화된 버젼으로 쓸 수 있다.
            // 이렇게 간략화된 for문을 forEach문 이라고 부른다.
            // forEach문은 다음과 같은 형태를 가지고 있다.
            // for(클래스 임시이름 : 콜렉션){
            //
            // }

            // 즉
            // for (int i = 0; i < LIST_TEACHER.size(); i++) {
            // Teacher t = LIST_TEACHER.get(i);
            // }
            // 를 간력화된 forEach 문으로 쓰면
            // for(Teacher t : LIST_TEACHER) {
            //
            // }
            // 가 된다.

            for (Teacher t : LIST_TEACHER) {
                if (t.getUsername().equals(username)) {
                    return true;
                }
            }

        } else if (type == 2) {
            for (Student s : LIST_STUDENT) {
                if (s.getUsername().equals(username))
                    return true;
            }
        }
        return false;
    }

    // 3. 메뉴를 보여주는 메소드
    private static void showMenu() {
        // 먼저 로그인 객체를 사용하여
        // 소속 반 학생 리스트를 정해준다.
        selectByGroup();

        while (true) {
            String message;
            int userChoice;

            if (logInTeacher != null) {
                message = new String("1. 전체 학생 목록 보기 2. 성적 미등록 학생 목록 보기 3. 교사 평가 보기 4. 종료");
                userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);

                if (userChoice == 1) {
                    // 전체 학생을 보는 메소드 실행
                    printAll();
                } else if (userChoice == 2) {
                    // 성적이 등록되지 않은 학생만 보는 메소드 실행
                    selectByInputSwitch();
                } else if (userChoice == 3) {
                    // 평가를 보여주는 메소드 실행
                } else if (userChoice == 4) {
                    System.out.println("인덱스 화면으로 돌아갑니다.");
                    break;
                }

            } else {
                message = new String("1. 학생 목록 보기 2. 교사 평가 보기 3. 종료");
                userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

                if (userChoice == 1) {
                    // 전체 학생을 보는 메소드 실행
                    printAll();
                } else if (userChoice == 2) {
                    // 평가를 보여주는 메소드 실행
                } else if (userChoice == 3) {
                    System.out.println("인덱스 화면으로 돌아갑니다.");
                    break;
                }

            }
        }
        groupStudents.clear();
        logInTeacher = null;
        logInStudent = null;

    }

    // 3-1. 로그인한 회원의 소속 반에 속한 학생들을
    // groupStudents에 저장하는 메소드
    private static void selectByGroup() {
        int group;
        if (logInStudent != null) {
            group = logInStudent.getGroup();
        } else {
            group = logInTeacher.getGroup();
        }

        for (Student s : LIST_STUDENT) {
            if (s.getGroup() == group) {
                groupStudents.add(s);
            }
        }
    }

    // 3-2-1, 3-3-1 전체 학생을 보는 메소드
    private static void printAll() {
        while (true) {
            // 지금 현재 소속반의 리스트를 for 문을 이용하여 전부 출력한 후에
            // 개별 보기, 뒤로 가기를 한다.
            // 단, for 문으로 출력시 성적이 입력되었는지를 따로 표시해준다.
            for (Student s : groupStudents) {
                String inputMark = new String();
                if (!s.isScoreInputSwitch()) {
                    inputMark = new String("X");
                } else {
                    inputMark = new String("O");
                }
                System.out.printf("번호: %03d 이름: %s 성적입력 여부 : %s\n", s.getId(), s.getName(), inputMark);
            }

            String message = new String("개별 보기할 번호를 입력해주세요. 만약 뒤로 가실려면 0을 입력해주세요.");
            int userChoice = ScannerUtil.nextInt(scanner, message);

            // 2. 사용자가 입력한 값이 유효한지 확인한다.
            // 만약 유효하지 않으면 유효한 값을 입력할 때까지 다시 입력을 받는다.

            // 2-1. 입력한 값이 유효한지 확인하기 위해 id 가 userChoice 인 Student 객체를 만든다.
            Student s = new Student();
            s.setId(userChoice);

            // 2-2. 사용자가 0을 입력하거나 혹은 유효한 번호를 입력했을 경우 두 경우가 모두 아닐 경우, 다시 입력을 받는다.
            // 유효한 번호란, 해당 소속 반에 속한 학생의 번호와 일치하는 것이 존재하는 경우이다.

            while (!(userChoice == 0 || groupStudents.contains(s))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
                s.setId(userChoice);

            }

            // 3. 유효한 번호가 나왔으므로 거기에 맞춰 코드를 실행한다.
            // userChoice가 0이 아닐 때에는 개별 보기 메소드를 호출해주고
            // userChoice가 0일 때에는
            // break 한다.

            if (userChoice == 0) {
                break;
            } else {
                // 개별 보기 하는 메소드를 실행한다.
                printOne(userChoice);
            }

        }

    }

    // 3-2-1-1, 3-3-1-1. 학생의 정보를 개별보기하는 printOne() 메소드
    private static void printOne(int id) {
        // 1. id 값을 이용하여 학생 객체를 리스트로부터 불러온다.
        Student s = selectStudentById(id);
        // 2. 해당 학생의 print() 메소드를 사용하여 학생의 정보를 출력한다.
        s.print();

        // 3. 로그인 한 사람이 학생인지 교사인지에 따라 결과를 다르게 해준다.

        if (logInStudent != null) {
            // logInStudent가 null이 아니라는 것은
            // 현재 로그인한 사람이 학생이라는 의미이므로
            // 1. 뒤로가기 만 선택 가능하게 만들어준다.
            String message = new String("1. 뒤로가기");
            ScannerUtil.nextInt(scanner, message, 1, 1);
        } else {
            // 현재 로그인한 사람이 선생이라는 의미이므로
            // 만약 성적 입력 여부가 false 이면
            // 성적 입력 메시지를 출력해주고
            // true이면 성적 수정 메시지를 출력해준다.

            // 하지만 입력과 수정의 차이는
            // 처음 안내 메시지가 다르고
            // 그리고 입력이 끝나면 해당 객체의 inputswitch 값을 true로 바꾼다 밖에 없다.
            // 즉 중간 과정은 모두 같으니 우리가 if를 통해서 메시지 + inputswitch만 변경하면 되지 않을까?

            // 또한, inputSwitch도 입력이건 수정이건 그냥 모두 inputSwitch 값을 true로 바꿔버리면 해결이 쉽다!

            String message;
            if (s.isScoreInputSwitch()) {
                message = new String("1. 수정 2. 뒤로가기");
            } else {
                message = new String("1. 입력 2. 돌아가기");
            }
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);

            if (userChoice == 1) {
                // 성적을 넣는 메소드 실행

                insertScore(id);

            } else if (userChoice == 2) {
                // 아무것도 하지 않음
            }

        }

    }

    // 3-2-1-1-1. 성적 입력을 담당하는 메소드
    private static void insertScore(int id) {
        Student s = selectStudentById(id);
        String message;
        // 국어 점수 입력
        message = new String("국어 점수를 입력해주세요.");
        s.setKorean(ScannerUtil.nextInt(scanner, message, 0, 100));

        // 영어 점수 입력
        message = new String("영어 점수를 입력해주세요.");
        s.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));
        // 수학 점수 입략
        message = new String("수학 점수를 입력해주세요.");
        s.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));

        // 만약 이미 기존에 성적을 입력한 학생이었으면
        // scoreInputSwitch 필드의 값이 true였는데 true로 바뀌게 된다.
        // 즉, 변화가 없는 것이다.

        // 만약 기존에 성적을 입력하지 않은 학생이었으면
        // scoreInputSwitch 필드 값이 false였는데 true로 바뀌게 된다.
        // 즉 해당 필드의 역할을 다 하게 되는 것이다.
        s.setScoreInputSwitch(true);

        // printOne 메소드를 다시 호출해서 입력/ 수정된 점수를 볼수 있게한다.
        printOne(id);

    }

    private static Student selectStudentById(int id) {
        Student s = new Student();
        s.setId(id);
        for (Student temp : groupStudents) {
            if (temp.equals(s)) {
                return temp;
            }
        }

        return null;
    }

    // 성적 입력이 아직 안된 학생을 리스트로 뽑아주는 메소드
    private static ArrayList<Student> selectByInputSwitch() {
        ArrayList<Student> list = new ArrayList<>();
        for (Student s : LIST_STUDENT) {
            if (!s.isScoreInputSwitch()) {
                list.add(s);
            }
        }
        return list;

    }

}
