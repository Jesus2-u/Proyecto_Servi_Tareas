pipeline {

    agent any 

    stages {

        stage('Checkout') {
            steps {
                echo 'Obteniendo código de la rama develop...'
                checkout scm 
            }
        }

        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias...'
                bat 'mvnw.cmd clean test' 
            }
        }

        stage('Build & Package') {
            steps {
                echo 'Compilando proyecto...'
                bat 'mvnw.cmd clean package -DskipTests' 
            }
        }

        stage('Deploy to Test Environment') {
            when {
                branch 'develop'
            }
            steps {
                echo 'Iniciando despliegue en entorno de pruebas...'

                // Simulación real de despliegue
                bat 'start java -jar target\\servitareas-0.0.1-SNAPSHOT.jar'

                echo 'Aplicación desplegada en entorno de pruebas (CD simulado OK)'
            }
        }
    }

    post {
        success {
            echo 'Pipeline exitoso: CI + CD simulado completado correctamente.'
        }
        failure {
            echo 'Pipeline falló: revisar pruebas o build.'
        }
    }
}
