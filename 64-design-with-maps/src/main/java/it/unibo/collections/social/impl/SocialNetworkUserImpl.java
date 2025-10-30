/**
 *
 */

package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.*;

/**
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 *
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    private final Map<String, Set<U>> followedUsers;

    /*
     *
     * [FIELDS]
     *
     * Define any necessary field
     *
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:
     *
     * think of what type of keys and values would best suit the requirements
     */
    public SocialNetworkUserImpl(
        final String firstName,
        final String lastName, 
        final String username,
        final Integer age) {
        super(firstName, lastName, username, age);
        this.followedUsers = new HashMap<>();      

    }
    /*
     * [CONSTRUCTORS]
     *
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     *
     * - firstName
     * - lastName
     * - username
     * - age and every other necessary field
     */
    /**
     * Builds a user participating in a social network.
     *
     * @param name
     *            the user firstname
     * @param surname
     *            the user lastname
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(
        final String name, 
        final String surname, 
        final String user, 
        final int userAge) {
        super(name, surname, user, userAge);
        this.followedUsers = new HashMap<>();    
    }

    /*
     * 2) Define a further constructor where the age defaults to -1
     */
    public SocialNetworkUserImpl(
        final String name, 
        final String surname, 
        final String user) {
        this(name, surname, user, -1);
    }
    /*
     * [METHODS]
     *
     * Implements the methods below
     * Adds a friend to the list of this user's current friends.
     *
     * @param group
     *            the group (circle) on which the user in going to be added
     * @param user
     *            the user to be added as a user followed
     * @return true if the user to be added as a followed person does not exist
     *         yet, false otherwise
     */
     
    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        if (!this.followedUsers.containsKey(circle)) {
            this.followedUsers.put(circle, new HashSet<>());
        }
        if (this.followedUsers.get(circle).add(user)) {
            return true;
        }
        return false;
    }

    /**
     *
     * [NOTE] If no group with groupName exists yet, this implementation must
     * return an empty Collection.
     */
    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        if (this.followedUsers.containsKey(groupName)) {
            return Collections.unmodifiableCollection(this.followedUsers.get(groupName));
        }
        if (!this.followedUsers.containsKey(groupName)) {
            return Collections.emptySet(); //differenza con emptyList
        }
        return null;
    }

    @Override
    public List<U> getFollowedUsers() {
        List<U> allFollowedUsers = new ArrayList<>();
        for (Set<U> usersSet : this.followedUsers.values()) {
            allFollowedUsers.addAll(usersSet);
        }
        return Collections.unmodifiableList(allFollowedUsers); //differenza collection-collections
    }
}
