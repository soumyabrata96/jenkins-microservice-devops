//Declarative
pipeline {
    //agent any
    agent { docker { image "eclipse-temurin:17-jdk-alpine" } }
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