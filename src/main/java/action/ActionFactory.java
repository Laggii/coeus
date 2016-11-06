package action;

import exception.ActionException;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * ActionFactory provides suitable Action object based on user "action" request parameter
 * Implementation of the FactoryMethod pattern
 */
public class ActionFactory {
    private static ActionFactory ourInstance = new ActionFactory();

    public static ActionFactory getInstance() {
        return ourInstance;
    }

    private ActionFactory() {
    }

    /**
     * Get Action object from ActionProvider by action request parameter
     *
     * @param name of the action from request
     * @return Action object
     * @throws ActionException
     */
    public Action getAction(String name) throws ActionException {
        if (name != null) {
            for (ActionProvider actionProvider : ActionProvider.values()) {
                if (name.equalsIgnoreCase(actionProvider.getName())) {
                    return actionProvider.getAction();
                }
            }
        }
        throw new ActionException("Action not found: " + name);
    }
}
