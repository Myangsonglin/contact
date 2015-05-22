package cc.young.contacts.model2;

import java.util.HashMap;
import java.util.Map;

import cc.young.contacts.actions.Action;
import cc.young.contacts.actions.AddContactAction;
import cc.young.contacts.actions.BootstrapAction;
import cc.young.contacts.actions.LoginAction;
import cc.young.contacts.actions.LogoutAction;
import cc.young.contacts.actions.RemoveContactAction;


public class ActionFactory {
	protected Map map = defaultMap();
	
	public ActionFactory() {
		super();
	}
	public Action create(String actionName) {
		Class klass = (Class) map.get(actionName);
		if (klass == null)
			throw new RuntimeException(getClass() + " was unable to find an action named '" + actionName + "'.");
		
		Action actionInstance = null;
		try {
			actionInstance = (Action) klass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionInstance;
	}
	protected Map defaultMap() {
		Map map = new HashMap();

		map.put("index", BootstrapAction.class);
		map.put("addContactAction", AddContactAction.class);
		map.put("removeContactAction", RemoveContactAction.class);
		map.put("loginAction", LoginAction.class);
		map.put("logoutAction", LogoutAction.class);

		return map;
	}
}
