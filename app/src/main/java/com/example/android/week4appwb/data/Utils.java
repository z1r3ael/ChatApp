package com.example.android.week4appwb.data;

import com.example.android.week4appwb.R;

import java.util.HashMap;

public class Utils {

    private static final HashMap<Integer, String> SenderNames = new HashMap<>();
    private static final HashMap<Integer, String> messagePreviews = new HashMap<>();
    private static final HashMap<Integer, String> messageSendingTimes = new HashMap<>();
    private static final HashMap<Integer, Integer> unreadMessageCounts = new HashMap<>();

    static {
        SenderNames.put(0, "Александр");
        SenderNames.put(1, "Тимофей");
        SenderNames.put(2, "Николай");
        SenderNames.put(3, "Таня");
        SenderNames.put(4, "Сергей");
        SenderNames.put(5, "Артем");
        SenderNames.put(6, "Виктория");
        SenderNames.put(7, "Михаил");
        SenderNames.put(8, "Артур");
        SenderNames.put(9, "Анастасия");

        messagePreviews.put(0, "Аварийный выход на высоте 30 тысяч футов. Иллюзия безопасности..");
        messagePreviews.put(1, "Если не знаешь, чего хочешь, умрешь в куче того, чего не хотел.");
        messagePreviews.put(2, "Ты хотел изменить свою жизнь, но не мог этого сделать сам. Я — то, кем ты хотел бы быть. Я выгляжу так, как ты мечтаешь выглядеть. Я трахаюсь так, как ты мечтаешь трахаться. Я умён, талантлив и, самое главное, свободен от всего, что сковывает тебя.");
        messagePreviews.put(3, "К черту совершенство. Не надо его добиваться. Надо развиваться. Пусть фишки ложатся как ложатся.");
        messagePreviews.put(4, "Лишь утратив всё до конца, мы обретаем свободу.");
        messagePreviews.put(5, "Вещи, которыми ты владеешь, в конце концов овладевают тобой.");
        messagePreviews.put(6, "Мы ходим на работу, которую ненавидим, чтобы купить барахло, которое нам не нужно.");
        messagePreviews.put(7, "Первое правило Бойцовского клуба: никому не рассказывать о Бойцовском клубе. Второе правило Бойцовского клуба: никогда никому не рассказывать о Бойцовском клубе.");
        messagePreviews.put(8, "Не разбив яиц, омлет не приготовишь.");
        messagePreviews.put(9, "Люди всё время меня спрашивают: знаю ли я Тайлера Дёрдена?");

        messageSendingTimes.put(0, "02:34");
        messageSendingTimes.put(1, "12:07");
        messageSendingTimes.put(2, "15:21");
        messageSendingTimes.put(3, "19:54");
        messageSendingTimes.put(4, "10:15");
        messageSendingTimes.put(5, "21:39");
        messageSendingTimes.put(6, "16:41");
        messageSendingTimes.put(7, "11:13");
        messageSendingTimes.put(8, "06:57");
        messageSendingTimes.put(9, "13:10");

        unreadMessageCounts.put(0, 2);
        unreadMessageCounts.put(1, 4);
        unreadMessageCounts.put(2, 6);
        unreadMessageCounts.put(3, 7);
        unreadMessageCounts.put(4, 8);
        unreadMessageCounts.put(5, 9);
        unreadMessageCounts.put(6, 10);
        unreadMessageCounts.put(7, 14);
        unreadMessageCounts.put(8, 18);
        unreadMessageCounts.put(9, 20);
    }

    public static int getRandomAvatarImage() {
        int result = 0;
        int randomFlag = (int) (Math.random() * 3);
        if (randomFlag == 3) {
            randomFlag = 2;
        }
        switch (randomFlag) {
            case 0:
                result = R.drawable.face_1;
                break;
            case 1:
                result = R.drawable.face_2;
                break;
            case 2:
                result = R.drawable.face_3;
                break;
        }
        return result;
    }

    public static String getRandomSenderName() {
        int randomFlag = (int) (Math.random() * 10);
        if (randomFlag == 10) {
            randomFlag = 9;
        }
        return SenderNames.get(randomFlag);
    }

    public static String getRandomMessagePreview() {
        int randomFlag = (int) (Math.random() * 10);
        if (randomFlag == 10) {
            randomFlag = 9;
        }
        return messagePreviews.get(randomFlag);
    }

    public static String getRandomMessageSendingTime() {
        int randomFlag = (int) (Math.random() * 10);
        if (randomFlag == 10) {
            randomFlag = 9;
        }
        return messageSendingTimes.get(randomFlag);
    }

    public static int getRandomUnreadMessageCount() {
        int randomFlag = (int) (Math.random() * 10);
        if (randomFlag == 10) {
            randomFlag = 9;
        }
        return unreadMessageCounts.get(randomFlag);
    }
}
