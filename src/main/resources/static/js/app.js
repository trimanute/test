angular.module('weatherApp', [])
  .controller('WeatherController', ['$scope', '$http', function($scope, $http) {
    // Initialize variables
    $scope.city = '';
    $scope.weatherData = null;
    $scope.loading = false;
    $scope.error = null;
    
    // Function to get weather data
    $scope.getWeather = function() {
      if (!$scope.city) return;
      
      $scope.loading = true;
      $scope.error = null;
      $scope.weatherData = null;
      
      $http.get('/api/weather/' + encodeURIComponent($scope.city))
        .then(function(response) {
          $scope.weatherData = response.data;
          $scope.loading = false;
        })
        .catch(function(error) {
          $scope.error = 'Failed to fetch weather data. Please try again.';
          $scope.loading = false;
          console.error('Error:', error);
        });
    };
  }]);