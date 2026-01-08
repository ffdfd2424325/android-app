import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Provider } from 'react-redux';
import { store } from './src/store';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import Icon from 'react-native-vector-icons/MaterialIcons';

// Импорт экранов
import HomeScreen from './src/screens/HomeScreen';
import CatalogScreen from './src/screens/CatalogScreen';
import CartScreen from './src/screens/CartScreen';
import FavoritesScreen from './src/screens/FavoritesScreen';
import ProfileScreen from './src/screens/ProfileScreen';

const Tab = createBottomTabNavigator();

const App = () => {
  return (
    <Provider store={store}>
      <SafeAreaProvider>
        <NavigationContainer>
          <Tab.Navigator
            screenOptions={({ route }) => ({
              tabBarIcon: ({ focused, color, size }) => {
                let iconName;

                switch (route.name) {
                  case 'Главная':
                    iconName = 'home';
                    break;
                  case 'Каталог':
                    iconName = 'category';
                    break;
                  case 'Корзина':
                    iconName = 'shopping-cart';
                    break;
                  case 'Избранное':
                    iconName = 'favorite';
                    break;
                  case 'Профиль':
                    iconName = 'person';
                    break;
                  default:
                    iconName = 'home';
                }

                return <Icon name={iconName} size={size} color={color} />;
              },
              tabBarActiveTintColor: '#2196F3',
              tabBarInactiveTintColor: 'gray',
            })}
          >
            <Tab.Screen name="Главная" component={HomeScreen} />
            <Tab.Screen name="Каталог" component={CatalogScreen} />
            <Tab.Screen name="Корзина" component={CartScreen} />
            <Tab.Screen name="Избранное" component={FavoritesScreen} />
            <Tab.Screen name="Профиль" component={ProfileScreen} />
          </Tab.Navigator>
        </NavigationContainer>
      </SafeAreaProvider>
    </Provider>
  );
};

export default App; 