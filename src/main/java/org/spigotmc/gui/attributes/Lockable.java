package org.spigotmc.gui.attributes;

/**
 * Implemented by Containers that contain elements that can be locked
 */
public interface Lockable {

    /**
     * Should toggle components in the implementing container that implements this interface
     *
     * @param reason the reason for toggling the containers components
     */
    void onLockToggle(final LockReason reason);

    /**
     * Reasons why a Container can be locked
     */
    enum LockReason {
        /**
         * Should be used if the container is starting up
         */
        START,
        /**
         * Should be used during the build phase of the build process
         */
        BUILD,
    }

}
