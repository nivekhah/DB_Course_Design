package DB;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDB {
    public User login(String account,String password,String type)
    {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet res=null;
        try{
            connection=DB.getConnection();
            String sql="select * from user where account=? and password=? and type=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,account);
            ps.setString(2,password);
            ps.setString(3,type);
            res=ps.executeQuery();
            if(res.next())
            {
                User user=new User();
                user.setAccount(res.getString("account"));
                user.setId(res.getInt("id"));
                user.setPassword(res.getString("password"));
                user.setType(res.getString("type"));
                return user;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            DB.close(connection,ps,res);
        }
        return null;
    }
}