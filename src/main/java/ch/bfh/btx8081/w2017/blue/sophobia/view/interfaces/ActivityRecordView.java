package ch.bfh.btx8081.w2017.blue.sophobia.view.interfaces;

import java.util.Date;

/**
 * @author kybup1
 */
public interface ActivityRecordView {

    public Date getDate();

    public void setDate(Date date);

    public int getSuccess();

    public void setSuccess(int success);

    public String getDescription();

    public void setDescription(String description);

    /**
     * Changes view to the create activity record state
     */
    public void setNewActivityRecord();

    /**
     * Changes the form after a new Activity Record is created and saved.
     * Adds the new id to the url.
     *
     * @param arid The id of the new activity record
     */
    public void changeToExistingActRec(int arid);

    /**
     * Clears the fields in the form and resets the view to the original state.
     */
    public void clearView();

    public void addListener(ActivityRecordViewListener listener);

    interface ActivityRecordViewListener {
        /**
         * This method is called when the save button is pressed.
         */
        public void save();

        /**
         * This method is called when the view is initialized.
         * If a new activity record has to be created the parameter arid should be -1.
         *
         * @param pid  Patient id
         * @param oid  Objective id
         * @param aid  Activity id
         * @param arid Activity record id
         */
        public void resolveIds(int pid, int oid, int aid, int arid);
    }

}
