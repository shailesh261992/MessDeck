messdeck.factory("scheduleService", function(calenderService, $log) {

	return {
		id : 1,
		getAvailableServicesOnDate : function(date) {

			this.id++;
			var service1 = {
				id : this.id,
				vaendorID : "1234",
				serviceType : "Tiffin",
				date : date,
				meal : [ {} ],
				cost : 100,
				subscribed : false

			};
			this.id++;

			var service2 = {
				id : this.id,
				vaendorID : "1234",
				serviceType : "Lunch",
				date : date,
				meal : [ {} ],
				cost : 100,
				subscribed : false

			};
			this.id++;
			var service3 = {
				id : this.id,
				vaendorID : "1234",
				serviceType : "Dinner",
				date : date,
				meal : [ {} ],
				cost : 100,
				subscribed : false

			};

			var services = [ service1, service2, service3 ];
			return services;

		},

		getMonthSchedule : function(date) {

			var month = date.getFullYear() + "" + date.getMonth();

			var monthSchedule = this.loadSchedule(month);

			if (!monthSchedule) {
				monthSchedule = [];
				var monthDates = calenderService.getDatesFromMonthToShow(date);

				for (var i = 0; i < monthDates.length; i++) {
					var services = this
							.getAvailableServicesOnDate(monthDates[i]);
					var daySchedule = new DaySchedule(monthDates[i], services);
					monthSchedule.push(daySchedule);
				}

			}
			return monthSchedule;

		},

		createSchedule : function(obj) {
			return new DaySchedule(obj.date, obj.avilableServices);
		},

		saveSchedule : function(month, monthSchedule) {
			$log.info('saving month schedule ');
			$log.info(monthSchedule);
			sessionStorage.setItem(month, JSON.stringify(monthSchedule))
		},

		loadSchedule : function(month) {
			$log.info('loading month schedule ');
			var monthSchedule = JSON.parse(sessionStorage.getItem(month));
			$log.info(monthSchedule);
			if (monthSchedule) {
				var temp = [];
				for (var i = 0; i < monthSchedule.length; i++) {
                     temp.push(new DaySchedule(new Date(monthSchedule[i].date),monthSchedule[i].avilableServices));
				}
			}
            monthSchedule = temp;
			return monthSchedule;

		}

	}

	function DaySchedule(date, avilableServices) {
		this.date = date;
		this.avilableServices = avilableServices;

	}

	function MessDeckService(obj) {
		this.id = obj.id;
		this.vaendorID = obj.vaendorID;
		this.serviceType = obj.serviceType;
		this.date = obj.date;
		this.meal = obj.meal;
		this.cost = obj.cost;
		this.subscribed = obj.subscribed;

		this.subscribeService = function() {

			this.subscribed = true;
		}

		this.unSubscribeService = function() {

			this.subscribed = false;
		}

	}

})