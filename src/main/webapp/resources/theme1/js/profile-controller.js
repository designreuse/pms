

chrisApp.controller('profileController',  ['$rootScope', '$scope', '$filter', '$location','userService','authService',
                                        'companyService','registrationService','roleService','siteService',
                                        function ($rootScope, $scope , $filter,$location,userService,authService,
                                        companyService,registrationService,roleService,siteService) {
	$scope.selectedRow =0;
	$scope.user ={};
	$scope.appUsers=[];	
	$scope.sessionUser={};
	$scope.loggedInUserDetail = [];
	$scope.selectedUser ={};
	
	$scope.passwordDetail = {};
		angular.element(document).ready(function(){		
			 
			 $scope.findLoggedInUserAccess();		
			 
		});
		
		console.log("ssshhh");
		console.log($scope.sessionUser);
		
		
				
		//---------------------X----------------------
				    
	    $scope.findLoggedInUserAccess=function(){
	    	$scope.getLoggedInUserAccess();
	    };	    
	   
	    $scope.getUserDetail=function(user){
	    	//console.log(user)
	    	$scope.selectedUser = angular.copy(user);
	    };
		    
   //--------- Services Call from User Controller -------------------	
		    
		    		    
		    $scope.getLoggedInUserAccess =function(){		    	
			   
				authService.loggedinUserAccess()
	    		.then(function(data) {
	    			console.log("loggedin user");	    			
	    			$scope.loggedInUserEmail = data.loggedInUserMail;
	    			console.log($scope.loggedInUserEmail);	    			
	    			if(data.statusCode == 200){
	    				$scope.sessionUser=data;
	    				$scope.getLoggedInUser($scope.sessionUser);
	    			}	    			
	            },
	            function(data) {
	                console.log('Unauthorized Access.')
	            }); 	
		    	
		    }
		    
		    $scope.getLoggedInUser=function(loginUser){
				userService.getLoggedInUser(loginUser)
	    		.then(function(data) {
	    			console.log(data)
	    			if(data.statusCode == 200){
	    				
	    				$scope.sessionUser=angular.copy(data.object);
	    				console.log('User object logged in')
	    				console.log($scope.sessionUser);
	    				$scope.loggedInUserDetail = angular.copy($scope.sessionUser);
	    			}
	            },
	            function(data) {
	                console.log('No User available')
	            });
			}
		    
		    $scope.updateProfile=function($securityForm){
		    	 /*if ($securityForm.$valid) {
		    	        //do stuff
		    	    }*/ 
		    	console.log($scope.loggedInUserDetail);
		    	
		    };
		    
		    $scope.updatePassword=function($securityForm){
		    	
		    	if ($scope.securityForm.$valid) {
	    	        //do stuff
		    		console.log($scope.passwordDetail);
		    		$scope.changePassword($scope.passwordDetail);
		    		
	    	    } 
		    	
		    	
		    };
		    
		    $scope.changePassword=function(userPasswords){
		    	userService.changePassword($scope.passwordDetail)
	    		.then(function(data) {
	    			console.log(data)
	    			if(data.statusCode==200){
						$('#profile-success-alert').show();
						$('#profile-success-alert').alert();
						$scope.successMessage = data.message;
						$('#profile-error-alert').hide();
						$('#passwordConfirmModal').modal('show');
					}else{
						$('#profile-error-alert').show();
						$('#profile-error-alert').alert();
						$('#profile-success-alert').hide();
						$scope.successMessage = data.message;
					}
	            },
	            function(data) {
	                console.log('No User available')
	                $('#profile-error-alert').show();
					$('#profile-error-alert').alert();
					$('#profile-success-alert').hide();
					$scope.successMessage = data.message;
	            });
		    }
		    
		    $scope.closeMessageWindow=function(){
		    	$('#profile-success-alert').hide();
		    	$('#profile-error-alert').hide();
				$scope.successMessage="";
			}
		    
		    
}]);

chrisApp.directive('validPasswordC', function () {
    return {
        require: 'ngModel',
        link: function (scope, elm, attrs, ctrl) {
            
              ctrl.$setValidity('noMatch', true);

                attrs.$observe('validPasswordC', function (newVal) {
                    if (newVal === 'true') {
                        ctrl.$setValidity('noMatch', true);
                    } else {
                        ctrl.$setValidity('noMatch', false);
                    }
                });
        }
    }
});
