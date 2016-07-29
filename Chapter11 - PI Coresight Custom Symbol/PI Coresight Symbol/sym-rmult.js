(function (CS) {
    var definition = {
        typeName: 'rmult',
		displayName: 'R Multi-Correlation',
        datasourceBehavior: CS.DatasourceBehaviors.Multiple,
        iconUrl: 'Images/r-multcorr.svg',
        inject: ['$http', '$sce', '$timeout', '$document'],
        getDefaultConfig: function () {
            return {
                DataShape: 'Table',
                Height: 400,
                Width: 400,
                Paths: null
            };
        },
        init: init
    };


    function init(scope, elem, $http, $sce, $timeout, $document) {

        var container = elem.find('#container')[0];
        var id = "rmult_" + Math.random().toString(36).substr(2, 16);
        container.id = id;
        scope.id = id;

        var timer;
        scope.resize = function (width, height) {
            $timeout.cancel(timer);
            timer = $timeout(function () {
                scope.config.Width = width;
                scope.config.Height = height;
                scope.showGraphic = false;
                scope.updateGraphic();
            }, 1000);
        }

        scope.updateGraphic = function () {

            if (scope.config.ElementsList == undefined) {
                return;
            }

            var postData = new Object();
            postData.StartTime = "1-Oct-2012";
            postData.EndTime = "1-Nov-2012";
            postData.Interval = "1h";
            postData.Width = scope.config.Width;
            postData.Height = scope.config.Width;
            postData.Paths = [];
            for (var i = 0; i < scope.config.ElementsList.length; i++) {
                postData.Paths.push(scope.config.ElementsList[i].Path);
            }
            if (postData.Paths.length < 2) {
                alert('The symnol must have at least 2 attributes!');
                return;
            }
            $http.post('http://marc-web-sql.marc.net:82/api/code', postData).then(function (response) {
                scope.plots = response.data;
                scope.plot = scope.plots[0];
                var currentElement = $document[0].getElementById(id)
                var currentElementWrappedID = angular.element(currentElement);
                currentElementWrappedID.height(scope.config.Width);
                scope.config.Height = scope.config.Width;
                scope.showGraphic = true;
            })
        }

        scope.dataUpdate = function (data) {
            if ((data == null) || (data.Rows.length == 0)) {
                return;
            }
            if (data.Rows[0].Path) {
                scope.config.ElementsList = data.Rows;
                scope.updateGraphic();
            }
        }

        scope.unsafe = function (s) {
            return $sce.trustAsHtml(s);
        }
        return { dataUpdate: scope.dataUpdate, resize: scope.resize };
    }
    CS.symbolCatalog.register(definition);
})(window.Coresight);