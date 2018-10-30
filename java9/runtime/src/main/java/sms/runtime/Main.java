package sms.runtime;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author tanglei
 * @date 18/10/30
 */
public class Main {

    public static void main(String[] args) {
        CommandRunner commandRunner = new CommandRunner();
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!Objects.equals(line = scanner.nextLine(), "quit")){
            //以空格为分隔符
            String[] inputs = line.split(" ");
            //目前简单处理，以第一空格前的输入为服务名，第二为方法名，后面的都认为是参数
            commandRunner.run(inputs[0],inputs[1], Arrays.asList(Arrays.copyOfRange(inputs, 2,inputs.length)));
        }
    }
}
