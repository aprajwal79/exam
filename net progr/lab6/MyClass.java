public class MyClass {
    public static char class_type(String address) {
        int i = 0;
        while (address.charAt(i) != '.') {
            i++;
        }
        int n = Integer.parseInt(address.substring(0, i));
        if (n < 128)
            return 'A';
        if (n < 192)
            return 'B';
        if (n < 224)
            return 'C';
        return 'D';
    }

    public static double[] no_of_hosts(String mask, char cla) {
        int c = 0;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '1')
                c++;
        }
        int hosts = 32 - c;
        int subnets = 0;
        if (cla == 'A') {
            subnets = c - 8;
        } else if (cla == 'B') {
            subnets = c - 16;
        } else if (cla == 'C') {
            subnets = c - 24;
        }
        return new double[] { Math.pow(2, hosts), Math.pow(2, subnets) };
    }

    public static String decimal_mask(String mask) {
        String[] m = mask.split("\\.");
        String d = "";
        for (int i = 0; i < 4; i++) {
            int a = Integer.parseInt(m[i], 2);
            d = d + String.valueOf(a) + ".";
        }
        return d;
    }

    public static String[] addresses(String ip, char cla) {
        String[] a = ip.split("\\.");
        String dba = "";
        String first = "";
        String last = "";
        if (cla == 'A') {
            dba = a[0] + ".255.255.255";
            last = a[0] + ".255.255.254";
        } else if (cla == 'B') {
            dba = a[0] + "." + a[1] + ".255.255";
            last = a[0] + "." + a[1] + ".255.254";
        } else if (cla == 'C') {
            dba = a[0] + "." + a[1] + "." + a[2] + ".255";
            last = a[0] + "." + a[1] + "." + a[2] + ".254";
        }
        int x = Integer.parseInt(a[3]);
        first = a[0] + "." + a[1] + "." + a[2] + "." + String.valueOf(x + 1);
        return new String[] { dba, last, first };
    }

    public static void main(String[] args) {
        String[] ip =new String[]{"192.168.1.0","1.3.5.192","20.0.0.0","132.6.128.0"};
        String [] mask=new
        String[]{"11111111.11111111.11111111.11100000","11111111.11111111.11111111.11000000",
        "11111111.11111000.00000000.00000000","11111111.11111111.11000000.00000000"};
        System.out.println("IP address"+"\t\t"+"Mask in decimal"+"\t\t"+"No of subnets"+"\t\t"+"No of hosts"+
        "\t\t"+"Direct Broadcast Address"+"\t\t"+"First host id"+"\t\t"+"Last host id");
        for(int i=0;i<4;i++){
            char cla=class_type(ip[i]);
            double [] a=no_of_hosts(mask[i],cla);
            String [] add=addresses(ip[i],cla);
            System.out.println(ip[i]+"\t\t"+decimal_mask(mask[i])+"\t\t"+a[0]+"\t\t"+a[1]+"\t\t\t"+add[0]+"\t\t\t"+ add[2]+"\t\t\t"+ add[1]);
        }
    }
}