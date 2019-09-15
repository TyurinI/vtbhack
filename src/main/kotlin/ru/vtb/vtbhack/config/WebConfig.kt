package ru.vtb.vtbhack.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import ru.vtb.vtbhack.socket.RoomHandler
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import ru.vtb.vtbhack.persistence.AnswerRepository
import ru.vtb.vtbhack.persistence.VotingRepository
import javax.servlet.MultipartConfigElement
import javax.sql.DataSource


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/",
                        "classpath:/static/", "classpath:/public/")
    }
}

@Configuration
@EnableWebSocket
class WSConfig(
        @Autowired val jdbcTemplate:JdbcTemplate,
        @Autowired val votingRepository: VotingRepository
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(RoomHandler(votingRepository, jdbcTemplate), "/vote").setAllowedOrigins("*")
    }
}

@Configuration
class FileConfig() {

    @Bean
    fun multipartConfigElement(): MultipartConfigElement {
        val factory = MultipartConfigFactory()
        factory.setMaxFileSize("128KB")
        factory.setMaxRequestSize("128KB")
        return factory.createMultipartConfig()
    }
}