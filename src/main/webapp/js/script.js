/**
 * 
 */

var messdeck = angular.module("messDeck", []).controller(
		"calendarController",
		function($scope, $log, calenderService, scheduleService) {

			$scope.currentMonth = new Date();
			$scope.monthSchedule = scheduleService
					.getMonthSchedule($scope.currentMonth);
			$scope.Tiffin = false;
			$scope.Lunch = false;
			$scope.Dinner = false;
			

			$scope.$watch("currentMonth", function(newValue, oldValue) {

				$scope.datesFromPreviousMonthToShow = calenderService
						.getDatesFromPreviousMonthToShow($scope.currentMonth);
				$scope.datesFromNextMonthToShow = calenderService
						.getDatesFromNextMonthToShow($scope.currentMonth);

				var oldKey = oldValue.getFullYear() + "" + oldValue.getMonth();
				scheduleService.saveSchedule(oldKey, $scope.monthSchedule)

				$scope.monthSchedule = scheduleService
						.getMonthSchedule($scope.currentMonth);

				$log.info('Month schedule :: ');
				$log.info($scope.monthSchedule);

			});

			$scope.subscribeServiceForMonth = function(serviceType)
			{
				$scope.monthSchedule.forEach(function(schedule, i) {
					schedule.avilableServices.forEach(function(service, i) {
						if (service.serviceType == serviceType) {
							service.subscribed = !$scope[serviceType]
						}

					})
				})
				
				$scope[serviceType] = !$scope[serviceType];
			}
			
			
			
			
			

		})
