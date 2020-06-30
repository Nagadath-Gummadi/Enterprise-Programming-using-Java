package lab1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class LabProg1 {
     public void createTable(String s) throws SQLException
    {
Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Student", "app","app");
            Statement st=con.createStatement();
st.executeUpdate("create table "+s+"(sno integer,name varchar(20),m1 integer,m2       integer,m3 integer)"); 
    }
    public void insertData(String s) throws SQLException
    {
       Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Student","app"   .      ,"app");
PreparedStatement ps=con.prepareStatement("insert into "+s+"  values(?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of records:");
        int n=sc.nextInt(),i=0;
        while(i<n)
        {
            System.out.println("Enter rollno:");
            ps.setInt(1,sc.nextInt());
            System.out.println("Enter name:");
            ps.setString(2,sc.next());
            System.out.println("Enter marks1:");
            ps.setInt(3,sc.nextInt());
            System.out.println("Enter marks2:");
            ps.setInt(4,sc.nextInt());
            System.out.println("Enter marks3:");
            ps.setInt(5,sc.nextInt());
            ps.executeUpdate();
            i++;
        } 
    }
    public void addCol(String s) throws SQLException
    {
          Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/ .  .  . . . . . . . .         Student","app ", "app");
          Statement st=con.createStatement();
          st.executeUpdate("alter table "+s+" add column total integer");
          st.executeUpdate("alter table "+s+" add column grade varchar(20)");
    }
    public void updateTable(String s) throws SQLException
    {
Connection  con=DriverManager.getConnection("jdbc:derby://localhost:1527/Student", "app","app");
Statement st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        ResultSet rs=st1.executeQuery("Select * from "+s);
        while(rs.next())
        {
            int ma1 = rs.getInt("m1");
            int ma2 = rs.getInt("m2");
            int ma3 = rs.getInt("m3");
            int t = ma1+ma2+ma3;
            rs.updateInt("total", t);    
            int p = t/3;
            if(p>90)
                rs.updateString("grade","A");
            else if(p>80&&p<=90)
                rs.updateString("grade","B");
            else if(p<=80&&p>70)
                rs.updateString("grade","C");
            else
                rs.updateString("grade","F");
            rs.updateRow();
        }
    }
    public void deleteRow(String s) throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Student",     . . . .     "app","app");
        PreparedStatement ps=con.prepareStatement("delete from "+s+" where sno=?");
        System.out.println("Enter rowno:");
        ps.setInt(1,sc.nextInt());
        ps.executeUpdate();
    }
    public void displaydetails(String s) throws SQLException
    {
        ResultSet rs;
        Statement st=con.createStatement();
        rs=st.executeQuery("select * from "+s);
        while(rs.next())
        {
            System.out.println("Sno:"+rs.getInt("sno"));
            System.out.println("SName:"+rs.getString("name"));
            System.out.println("Grade:"+rs.getString("grade"));
            System.out.println("Total:"+rs.getInt("total"));
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String cn = "studtab";
            while(true)
            {
                LabProg1 ob=new LabProg1();
                System.out.println("Menu:");
               System.out.println("0.Exit\n1.Create\n2.Insert\n3.Alter\n4.Update\n5.Display by  sno\n   .              6.Display");
                System.out.println("Choose your option:");
                Scanner sc=new Scanner(System.in);
                int opt=sc.nextInt();
                switch(opt)
                {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println("Enter table name you want to create:");
                        cn=sc.next();
                        ob.createTable(cn);
                        System.out.println("Succesfully created");
                        break;
                    case 2:
                        System.out.println(cn);
                        ob.insertData(cn);
                        System.out.println("Succesfully inserted");
                        break;
                    case 3:
                        ob.addCol(cn);
                        System.out.println("Successfull added columns");
                        break;
                    case 4:
                        ob.updateTable(cn);
                        System.out.println("Succesfully updated");
                        break;
                    case 5:
                        ob.deleteRow(cn);
                        System.out.println("Succesfully deleted");
                        break;
                    case 6:
                        ob.displaydetails(cn);
                        break;
                }
            }
    }    
}
