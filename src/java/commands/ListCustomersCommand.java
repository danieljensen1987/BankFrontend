package commands;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import servlets.Factory;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import security.SecurityRole;

public class ListCustomersCommand extends TargetCommand
{

    public ListCustomersCommand(String target, List<SecurityRole> roles)
    {
        super(target, roles);
    }

    @Override
    public String execute(HttpServletRequest request)
    {
        BankManager manager = Factory.getInstance().getManager();
        
        Collection<CustomerSummary> customers = manager.listCustomers();
        
        request.setAttribute("customers", customers);
        request.setAttribute("message", "This is a not test");
        
        return super.execute(request);
    }
    
}
