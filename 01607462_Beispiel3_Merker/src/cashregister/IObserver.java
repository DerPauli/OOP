/**
 * @author Paul Merker
 * @matrNr 01607462
 */

package cashregister;

import managementserver.ISubjectManagementServer;

public interface IObserver {
	void notifyChange(ISubjectManagementServer subject);
	void deactivateNotifications(ISubjectManagementServer subject);
	void activateNotifications(ISubjectManagementServer subject);
}
