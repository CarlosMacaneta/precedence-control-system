package com.cm.precedencecontrolsystem.data.repository

import com.cm.precedencecontrolsystem.data.source.LoginDataSource

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {


}