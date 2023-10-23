package baseball;

import baseball.util.ValidateNumber;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        //1. 게임 시작
        System.out.println("숫자 야구 게임을 시작합니다.");
        boolean end = false;
        boolean game = false;
        int strike;
        int ball;
        String answer;
        ValidateNumber validateNumber = new ValidateNumber();


        //2. 게임 진행
        while (!end) {
            //2.1. 랜덤 숫자 생성
            List<Integer> computer = new ArrayList<>();
            while (computer.size() < 3) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);
                if (!computer.contains(randomNumber)) {
                    computer.add(randomNumber);
                }
            }
            //2.2. "숫자를 입력해주세요 :" 출력과 함께 사용자 입력
            while (!game) {
                System.out.print("숫자를 입력해주세요 : ");
                answer = Console.readLine();
                validateNumber.isInRange(answer);
                validateNumber.isLengthCorrect(answer);
                validateNumber.isNotDuplicate(answer);
                //2.4. 랜덤 숫자와 사용자 입력 숫자 값 비교
                strike = 0;
                ball = 0;
                for (int i = 0; i < answer.length(); i++) {
                    if (computer.contains((answer.charAt(i)) - '0')) {
                        ball++;
                    }
                }
                for (int i = 0; i < answer.length(); i++) {
                    if (computer.get(i) == answer.charAt(i) - '0') {
                        strike++;
                    }
                }
                ball -= strike;
                //2.4.1
                String temp_string = "";
                if (ball == 0 && strike == 0) temp_string = "낫싱";
                if (ball != 0) temp_string += ball + "볼 ";
                if (strike != 0) temp_string += strike + "스트라이크";
                System.out.println(temp_string.trim());
                //2.4.2
                if (strike == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    game = true;
                }

            }

            //3. 게임 지속 여부 판단
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            String endNumber = Console.readLine();
            validateNumber.isEndNumber(endNumber);
            if (endNumber.equals("2")) {
                end = true;
            } else game = false;
            //예외 처리 필요
        }
    }
}
