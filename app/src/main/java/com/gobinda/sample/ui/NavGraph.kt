package com.gobinda.sample.ui

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.sample.ui.auth.SignInScreen
import com.gobinda.sample.ui.auth.SignInViewModel
import com.gobinda.sample.ui.auth.SignUpScreen
import com.gobinda.sample.ui.test.compose.DetailsScreen
import com.gobinda.sample.ui.test.compose.HomeListScreen
import com.gobinda.sample.ui.test.compose.NavDrawerScreen
import com.gobinda.sample.ui.test.compose.TabPagerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    vm: SignInViewModel = hiltViewModel()
) {

    NavHost(navController = navController, startDestination = if(vm.anyUserLoggedIn()) "home" else "sign_in") {
        composable("sign_in") {
            SignInScreen(onNavigateHome = {
                navController.navigate("home") {
                    popUpTo("sign_in") { inclusive = true }
                    launchSingleTop = true
                }
            }, onNavigateSignUp = {
                navController.navigate("signup") {
                    launchSingleTop = true
                }
            })
        }
        composable("signup") {
            SignUpScreen {
                navController.navigate("sign_in") {
                    popUpTo("signup") { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
        composable("home") {

            NavDrawerScreen(drawerState, onItemClick = {
                coroutineScope.launch {
                    when(it){
                        "logout" -> {
                            vm.logout()
                            navController.navigate("sign_in") {
                                popUpTo("signup") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                        else -> {
                            delay(500)
                            drawerState.close()
                        }
                    }
                }
            }) {
                HomeListScreen(onOpenDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }, onItemClick = {
                    navController.navigate("details")

                }, onButtonItemClick = {
                    navController.navigate("tab_pager")
                })
            }
        }
        composable("tab_pager") {
            TabPagerScreen(onBackClicked = {
                navController.popBackStack()
            })
        }

        composable("details") {
            DetailsScreen(onBack = {
                navController.popBackStack()
            })
        }
    }

}