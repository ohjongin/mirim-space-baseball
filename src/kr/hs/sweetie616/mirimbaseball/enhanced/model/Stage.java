package kr.hs.sweetie616.mirimbaseball.enhanced.model;

/**
 * Describe about this class here...
 *
 * @author ohjongin
 * @since 1.0
 * 14. 11. 21
 */
public class Stage {
    public static final int MAX_STAGE = 3;
    public static final int MAX_NUMBER_LENGTH = 5;

    protected static final int[] stageSize = {3, 4, 5};
    protected static int currStage = 1;

    public static int getNumberLength() {
        return getNumberLength(currStage);
    }

    public static int getNumberLength(int stage) {
        return (stage < 1 || stage > stageSize.length) ? 3 : stageSize[stage - 1];
    }

    public static int getCurrStage() {
        return currStage;
    }

    public static void setCurrStage(int currStage) {
        Stage.currStage = currStage;
    }
}
