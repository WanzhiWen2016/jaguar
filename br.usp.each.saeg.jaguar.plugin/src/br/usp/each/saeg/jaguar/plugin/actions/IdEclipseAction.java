package br.usp.each.saeg.jaguar.plugin.actions;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import br.usp.each.saeg.jaguar.plugin.JaguarPlugin;

public class IdEclipseAction extends Action implements IWorkbenchAction {

	private static final String ID = "br.usp.each.saeg.jaguar.plugin.actions.IdEclipseAction";
	private IProject project;
	private Action startAction;
	private UUID uuid;
	private String POPUP_TITLE = "Eclipse - ID generation";
	private String POPUP_MESSAGE = "\nThis ID number was saved in the file ID in the desktop area. \nIt will be used to fill out the response form.";
	private String FILENAME = System.getProperty("user.dir") + "/Desktop/id";
		
	public IdEclipseAction(IProject project, Action start) {
		uuid = UUID.randomUUID();
		this.project = project;
		this.startAction = start;
	}

	public void run(){
		System.out.println("id generation: " + uuid);
		JaguarPlugin.ui(project, this, "id generation: " + uuid);
		openDialogPopup("Your ID is: " + uuid + POPUP_MESSAGE);
		saveIdFile(uuid);
		this.setEnabled(false);
		this.startAction.setEnabled(true);
	}
	
	private void saveIdFile(UUID uuid) {
		try {
            FileUtils.write(new File(FILENAME), uuid.toString(), true);
        } catch (Exception e) {
            errorStatus("error while writing log file", e);
        }
	}

	private void openDialogPopup(String idMessage) {
		MessageDialog.openError(new Shell(),POPUP_TITLE,idMessage);
	}

	@Override
	public void dispose() {
	}
	
	public IStatus errorStatus(String message, Throwable t) {
        return new Status(IStatus.ERROR, ID, IStatus.ERROR, message, t);
	}
	

}
