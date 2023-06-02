//Declarative
pipeline {
    agent any
    stages
    {
        stage('Build') {
            steps{
                echo "Coupon-Service Microservic : Build"
            }
        }
        stage('Test') {
            steps{
                echo "Coupon-Service Microservic : Test"
            }
        }
        stage('Integration Test') {
            steps{
                echo "Coupon-Service Microservic : Integration Test"
            }
        }
    }
    post{
        always{
            echo "I will show everyone my capabilities !!"
        }
        success{
            echo "Fuck Capgemini and BDN !! I will write my own success story !!"
        }
        failure{
            echo "I am total failure !!"
        }
    }
}