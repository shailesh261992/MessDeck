/**
 * 
 */

messdeck.factory("calenderService", function($log) {

	return {

		currentDate : function() {
			return new Date();
		},
		dayName : function(date) {
			var dayNames = [ "Sunday", "Monday", "Tuesday", "Wednesday",
					"Thursday", "Friday", "Saturday" ]
			return dayNames[date.getDay()];
		},

		daysInMonth : function daysInMonth(date) {
			return new Date(date.getFullYear(), date.getMonth() + 1, 0)
					.getDate();
		},

		firstDateOfMonth : function(date) {
			return new Date(date.getFullYear(), date.getMonth(), 1, 18)
		},

		lastDateOfMonth : function(date) {
			return new Date(date.getFullYear(), date.getMonth(), this
					.daysInMonth(date), 18)
		},

		getDatesFromPreviousMonthToShow : function(date) {
			var firstDate = this.firstDateOfMonth(date);
			var firstDay = firstDate.getDay();
			var datesToDraw = [];

			if (firstDay > 0) {
				var cnt = firstDay;

				while (cnt > 0) {

					var d = new Date(firstDate);
					d.setDate(d.getDate() - cnt)
					datesToDraw.push(d);
					cnt--;
				}

			}
			//$log.info('See below');
			//$log.info(datesToDraw);
			return datesToDraw;

		},

		getDatesFromNextMonthToShow : function(date) {
			var lastDate = this.lastDateOfMonth(date);
			var lastDay = lastDate.getDay();
			var datesToDraw = [];

			if (lastDay != 6) {
				var limit = 6 - lastDay;
				var cnt = 1;
				while (cnt <= limit) {

					var d = new Date(lastDate);
					d.setDate(d.getDate() + cnt)
					datesToDraw.push(d);
					cnt++;
				}

			}

			//$log.info('See below');
			//$log.info(datesToDraw);
			return datesToDraw;

		},

		getDatesFromMonthToShow : function(date) {
			var firstDate = this.firstDateOfMonth(date);
			var lastDate = this.lastDateOfMonth(date);
			var lastDateInMonth = lastDate.getDate();
			var datesToDraw = [];

			var cnt = 0;

			while (cnt < lastDateInMonth) {
				var d = new Date(firstDate);
				d.setDate(d.getDate() + cnt);
				cnt++;
				datesToDraw.push(d);
			}

			//$log.info('See below');
			//$log.info(datesToDraw);
			return datesToDraw;

		},

		isDatesEqulas : function(date1, date2) {
			if (date1.getFullYear() == date2.getFullYear()
					&& date1.getMonth() == date2.getMonth()
					&& date1.getDate() == date2.getDate()) {
				return true;
			} else {
				return false;
			}
		}

	}

})