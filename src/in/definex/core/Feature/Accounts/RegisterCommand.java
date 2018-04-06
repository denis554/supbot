package in.definex.core.Feature.Accounts;

import in.definex.core.ChatSystem.Client;
import in.definex.core.Feature.Command;
import in.definex.core.String.FeatureAndCommandDescription;
import in.definex.core.String.Strings;

/**
 * Created by adam_ on 04-12-2017.
 */
public class RegisterCommand extends Command {

    public static final String name = "register";

    public RegisterCommand() {
        super(name, 0, Client.Role.Unregistered);
    }

    @Override
    protected String compute(Client client, String[] args) {

        if(client.getRole().hasPermission(Client.Role.Member))
            return Strings.allReadyRegistered;

        client.saveToDatabase();
        client.changeRole(Client.Role.Member);

        return String.format(Strings.successfullyRegisteredFormat, client.getName());
    }

    @Override
    public Helper getHelper() {
        return new Helper(
                FeatureAndCommandDescription.registerTemplateExample,
                FeatureAndCommandDescription.registerTemplateExample,
                FeatureAndCommandDescription.registerDescription
        );
    }
}
