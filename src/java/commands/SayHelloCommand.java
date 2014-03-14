package commands;

import dk.cphbusiness.bank.contract.BankManager;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;
import servlets.Factory;

public class SayHelloCommand extends TargetCommand
{

    public SayHelloCommand(String target, List<SecurityRole> roles)
    {
        super(target, roles);
    }

    @Override
    public String execute(HttpServletRequest request)
    {
        BankManager manager = Factory.getInstance().getManager();
        String greeting = manager.sayHello("Daniel");
        request.setAttribute("greeting", greeting);
        return super.execute(request);
    }
    
    
    
}
