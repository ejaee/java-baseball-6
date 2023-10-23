package baseball.utils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class UtilityTest {

    @Test
    void 정수는_예외_발생하지_않음() {
        // given
        String input = "1234";

        // when, then
        assertThatCode(() -> Utility.checkIfIsInteger(input))
            .doesNotThrowAnyException();;
    }

    @Test
    void 정수가_아니면_예외가_발생함() {
        // given
        String input = "hello";

        // when, then
        assertThatThrownBy(() -> Utility.checkIfIsInteger(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("입력값은 정수이어야 합니다.");
    }

    @Test
    void 게임의_룰에_맞게_공의_개수를_입력하면_예외가_발생하지_않음() {
        // given
        String input = "123";
        int numberBalls = 3;

        // when, then
        assertThatCode(() -> Utility.checkIfIsValidLength(numberBalls, input))
            .doesNotThrowAnyException();;
    }

    @Test
    void 게임의_룰에_맞지않는_공의_개수를_입력하면_예외가_발생함() {
        // given
        String input = "1234";
        int numberBalls = 3;

        // when, then
        assertThatThrownBy(() -> Utility.checkIfIsValidLength(numberBalls, input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("공의 개수는 " + numberBalls + "개를 입력해야 합니다.");
    }

}