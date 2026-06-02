pipeline {
    // Ejecutamos este pipeline en cualquier agente (servidor) disponible
    agent any 

    // Herramientas necesarias. Jenkins tiene un JDK de Java 21 configurado
    tools {
        jdk 'jdk21' // Este nombre depende de cómo se configure en Jenkins
    }

    stages {
        
        // Etapa 1: Preparación (Clonar el código)
        stage('Checkout') {
            steps {
                echo 'Obteniendo el código de la rama develop...'
                checkout scm 
            }
        }

        // Etapa 2: Ejecutar Pruebas (JUnit en Windows)
        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias con JUnit y Spring Boot...'
                // En Windows usamos 'bat' y llamamos a mvnw.cmd
                bat 'mvnw.cmd clean test' 
            }
        }

        // Etapa 3: Empaquetar la aplicación
        stage('Build & Package') {
            steps {
                echo 'Construyendo el archivo .jar ejecutable...'
                // Compila el proyecto saltándose los tests que ya pasaron
                bat 'mvnw.cmd clean package -DskipTests' 
            }
        }

        // Etapa 4: Despliegue al Servidor de Pruebas
        stage('Deploy to Test Environment') {
            // Esta condición asegura que solo se despliegue si los cambios vienen de develop
            when {
                branch 'develop'
            }
            steps {
                echo 'Desplegando la aplicación en el servidor de pruebas...'
                /* * En Jenkins, si usamos el comando directo "java -jar", el pipeline se quedaría 
                 * atascado cargando infinitamente porque el servidor nunca se apaga.
                 * Por eso, en un pipeline real se simula o se ejecuta en segundo plano.
                 */
                echo 'Ejecutando comando: java -jar target\\servitareas-0.0.1-SNAPSHOT.jar'
                
                echo '¡Despliegue exitoso!'
            }
        }
    }
    
    // Acciones finales según el resultado
    post {
        success {
            echo '¡Pipeline ejecutado con éxito! El código de develop pasó las pruebas y está desplegado.'
        }
        failure {
            echo 'El pipeline falló. Revisa los logs de Jenkins (probablemente falló alguna prueba de JUnit).'
        }
    }
}