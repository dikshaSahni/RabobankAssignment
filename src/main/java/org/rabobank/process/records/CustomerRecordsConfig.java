/**
 * 
 */
package org.rabobank.process.records;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author diksha.sahni
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "org.rabobank.process.statements"})
public class CustomerRecordsConfig {

}
