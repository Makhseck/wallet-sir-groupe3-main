pipeline{
    agent any
    stages{
            stage('Source'){
            steps{
            git branch: 'main', url:'https://github.com/Makhseck/wallet-sir-groupe3-main.git'
            }
            }
            stage('Build'){
                        steps{
                              bat 'mvnw clean package org.jacoco:jacoco-maven-plugin:prepare-agent'
                        }
                }

                 stage('SonarQube Analysis'){
                                        steps{
                                              bat 'mvnw  sonar:sonar'
                                        }
                                }
            stage('Build Docker image'){
                                         steps{
                                           bat 'docker build -t sirsoir2022/sir-soir-groupe3:latest .'
                                               }
                                         }
            stage('login to docker Hub'){
                                      steps{
                                             bat 'docker login -u sirsoir2022 -p sirsoir2022'
                                                        }
                                             }
            stage('Push to docker Hub'){
                                       steps{
                                           bat 'docker push sirsoir2022/sir-soir-groupe3:latest'
                                                   }
                                           }

            stage('Deploy to Kubernetes') {
                                       steps{
                                                       bat 'kubectl apply -f deploymentServices.yml'
                                                  }
                                       }

    }

}