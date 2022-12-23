package CircuitSim;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;


public class CircuitSim {
    public String netlist;

    public CircuitSim(){
        netlist = null;
    }

    // 调用Python脚本进行仿真, 并获取仿真结果
    public String getResult(){
        String result = null;
        String newResult=null;
        try {
            if(netlist == null){
                return null;
            }
            // String netlist = "V0 1 0 10.0\n"
            //                 +"W0 1 2\n"
            //                 +"R0 2 3 3.0\n"
            //                 +"W1 3 4\n"
            //                 +"W2 4 5\n"
            //                 +"W3 5 0\n";
            
            String base64_netlist = Base64.getEncoder().encodeToString(netlist.getBytes("utf-8"));
            ProcessBuilder processBuilder = new ProcessBuilder("python", "./src/cal.py", base64_netlist);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = 
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            result = builder.toString();
            String[] lines = result.split("\n");
            newResult = new String("");
            for (String string : lines) {
                if(string.length()==1){ // 注意, 此处问题未完全处理, 暂时忽略len==1的多余字符串
                    continue;
                }
                System.out.println(string.length());
                newResult = newResult.concat(string+"\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return newResult;
    }
}
